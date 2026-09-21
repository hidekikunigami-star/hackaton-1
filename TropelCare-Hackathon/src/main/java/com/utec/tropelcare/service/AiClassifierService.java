package com.utec.tropelcare.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.*;

@Service
public class AiClassifierService {
    public record Classification(String signalType,String severity,String assignedUnit,String recommendedAction,String personalityNote) {}
    private static final Map<String,String> UNITS=Map.of(
        "HAMBRE","Laboratorio de Nutricion","ABANDONO","Unidad de Bienestar","MUTACION","Division Genetica",
        "FUGA","Equipo de Contencion","CONFLICTO","Consejo de Mediacion","REPRODUCCION_MASIVA","Control Demografico","SENAL_CORRUPTA","Archivo de Senales");
    private static final Set<String> SEVERITIES=Set.of("LEVE","MODERADO","GRAVE","CRITICO");
    private final RestClient client; private final ObjectMapper mapper; private final String model;
    public AiClassifierService(ObjectMapper mapper,@Value("${github.models.url}") String url,@Value("${github.token:}") String token,@Value("${github.models.model-id:gpt-4o-mini}") String model){
        this.mapper=mapper;this.model=model;this.client=RestClient.builder().baseUrl(url).defaultHeaders(h->{h.setContentType(MediaType.APPLICATION_JSON);if(token!=null&&!token.isBlank())h.setBearerAuth(token);}).build();
    }
    public Classification classify(String rawContent){
        String prompt="Eres el sistema de clasificación de señales del TropelCare Signal Engine, desarrollado por Tuckersoft.\n"+
        "Recibes señales emitidas por criaturas digitales llamadas Tropeles y debes clasificarlas.\n"+
        "Responde ÚNICAMENTE con este JSON en una sola línea, sin texto adicional, sin markdown, sin bloques de código:\n"+
        "{\"signalType\":\"<TIPO>\",\"severity\":\"<GRAVEDAD>\",\"assignedUnit\":\"<UNIDAD>\",\"recommendedAction\":\"<acción breve y concreta en español>\",\"personalityNote\":\"<máximo 2 oraciones divertidas en español sobre el estado emocional del Tropel>\"}\n"+
        "Tipos válidos: HAMBRE, ABANDONO, MUTACION, FUGA, CONFLICTO, REPRODUCCION_MASIVA, SENAL_CORRUPTA\n"+
        "Gravedades válidas: LEVE, MODERADO, GRAVE, CRITICO\n"+
        "Unidades válidas: Laboratorio de Nutricion, Unidad de Bienestar, Division Genetica, Equipo de Contencion, Consejo de Mediacion, Control Demografico, Archivo de Senales\n"+
        "Señal a clasificar:\n"+rawContent;
        Map<String,Object> body=Map.of("model",model,"messages",List.of(Map.of("role","system","content",prompt),Map.of("role","user","content",rawContent)),"temperature",0.0);
        String response=client.post().uri("/chat/completions").body(body).retrieve().body(String.class);
        try { JsonNode root=mapper.readTree(response); String content=root.path("choices").path(0).path("message").path("content").asText(); return parse(content); }
        catch(Exception e){throw new IllegalArgumentException("Respuesta de IA inválida",e);}
    }
    public Classification parse(String text){
        try {
            int a=text.indexOf('{'), b=text.lastIndexOf('}'); if(a<0||b<=a) throw new IllegalArgumentException("JSON no encontrado");
            JsonNode n=mapper.readTree(text.substring(a,b+1)); String type=n.path("signalType").asText(null), sev=n.path("severity").asText(null), unit=n.path("assignedUnit").asText(null), action=n.path("recommendedAction").asText(null); String note=n.path("personalityNote").isMissingNode()?null:n.path("personalityNote").asText(null);
            if(!UNITS.containsKey(type)||!SEVERITIES.contains(sev)||!Objects.equals(UNITS.get(type),unit)||action==null||action.isBlank()) throw new IllegalArgumentException("Valores de clasificación inválidos");
            return new Classification(type,sev,unit,action,note);
        } catch(Exception e){throw new IllegalArgumentException("JSON de clasificación inválido",e);}
    }
    public static Classification fallback(){return new Classification("SENAL_CORRUPTA","LEVE","Archivo de Senales","Archivar la señal y revisar manualmente si se repite.",null);}
}
