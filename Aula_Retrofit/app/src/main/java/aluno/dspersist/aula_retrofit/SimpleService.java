package aluno.dspersist.aula_retrofit;

import java.io.IOException;
import java.util.List;

import aluno.dspersist.aula_retrofit.MainActivity.Distrito;
import aluno.dspersist.aula_retrofit.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimpleService {
    private static final String API_URL = "https://servicodados.ibge.gov.br/";

    public List<Distrito> doRequest() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Distrito>> call = service.getDistritos("2311306");

        // Executa a chamada síncrona
        retrofit2.Response<List<Distrito>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Erro na resposta: " + response.errorBody().string());
        }
    }
}
