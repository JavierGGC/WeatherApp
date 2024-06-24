import com.example.weatherapp.repository.weather.CityRepository
import com.example.weatherapp.data.model.GeoApi
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(GeoApi::class.java) }
}

val repositoryModule = module {
    single { CityRepository(get()) }
}

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}