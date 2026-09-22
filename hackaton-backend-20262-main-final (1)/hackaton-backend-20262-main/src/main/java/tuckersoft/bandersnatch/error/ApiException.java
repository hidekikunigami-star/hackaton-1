package tuckersoft.bandersnatch.error;
public class ApiException extends RuntimeException { public final int status; public final String error; public ApiException(int s,String e,String m){super(m);status=s;error=e;} }
