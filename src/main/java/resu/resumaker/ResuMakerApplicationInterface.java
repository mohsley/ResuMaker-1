package resu.resumaker;
import javax.jws.*;
import javax.xml.ws.*;
// @webmethod @webparam @webresults -- this is the operation that will be exposed by the WSDL
@WebService()
public interface ResuMakerApplicationInterface {
    @WebResult(name=ResuMakerApplication)
    public void Resumaker (ResuMakerApplication(@WebParam()){

    }


}
