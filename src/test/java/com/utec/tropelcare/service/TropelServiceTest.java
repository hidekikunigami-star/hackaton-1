package com.utec.tropelcare.service;
import com.utec.tropelcare.dto.*; import com.utec.tropelcare.entity.*; import com.utec.tropelcare.exception.BusinessException; import com.utec.tropelcare.repository.*; import org.junit.jupiter.api.*; import org.mockito.*; import java.util.Optional; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
class TropelServiceTest{
 @Mock TropelRepository tropels; @Mock SectorRepository sectors; @Mock GuardianRepository guardians; TropelService service; @BeforeEach void setup(){MockitoAnnotations.openMocks(this);service=new TropelService(tropels,sectors,guardians);}
 @Test void fullSectorThrowsBusinessException(){Sector s=new Sector();s.setId(1L);s.setCapacity(1);s.setCurrentLoad(1);when(sectors.findById(1L)).thenReturn(Optional.of(s));Guardian g=new Guardian();g.setId(1L);when(guardians.findById(1L)).thenReturn(Optional.of(g));when(tropels.findByName("BipBop")).thenReturn(Optional.empty());assertThrows(BusinessException.class,()->service.create(new TropelRequest("BipBop","GLITCHY",1L,1L)));}
}
