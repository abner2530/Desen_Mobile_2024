package aluno.dspersist.aula_retrofit;

import java.util.List;

import aluno.dspersist.aula_retrofit.MainActivity.Distrito;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("api/v1/localidades/municipios/{municipio}/distritos")
    Call<List<Distrito>> getDistritos(@Path("municipio") String municipio);
}
