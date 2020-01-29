package com.ardakazanci.sampleandroidmvvmproject2.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApi
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel : ViewModel() {


    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    // Coroutines initialize
    private val viewModelJob = Job()

    /**
     * Dispatchers.Main dispatcher çalışması için UI iş parçacığını kullanır.
     * Retrofit tüm çalışmalarını bir arka plan iş parçacığı üzerinde yaptığı için, kapsam için başka
     * bir iş parçacığı kullanmak için hiçbir neden yoktur.
     */
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )


    init {

        getTodoProperties()

    }


    private fun getTodoProperties() {


        coroutineScope.launch {
            val getTodosDeferred =
                TodoApi.retrofitService.getTodos()
            try {
                /**
                 * Deferred nesnesindeki await () yönteminin çağrılması, değer hazır olduğunda ağ çağrısının sonucunu döndürür.
                 * Await () yöntemi engellenmez, bu nedenle Mars API hizmeti geçerli iş parçacığını engellemeden ağdan veri alır -
                 * bu önemlidir, çünkü UI iş parçacığının kapsamındayız.
                 * Görev tamamlandığında, kodunuz kaldığı yerden çalışmaya devam eder. Await yöntemi veriyi beklerken MainThread serbesttir.
                 */
                val listTodos = getTodosDeferred.await()
                _response.value =
                    "Success: ${listTodos.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
