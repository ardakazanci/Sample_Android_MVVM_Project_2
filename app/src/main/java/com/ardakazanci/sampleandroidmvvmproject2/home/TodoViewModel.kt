package com.ardakazanci.sampleandroidmvvmproject2.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApi
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApiFilter
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


enum class TodoApiStatus {
    LOADING,
    ERROR,
    DONE
}


class TodoViewModel : ViewModel() {


    private val _status = MutableLiveData<TodoApiStatus>()
    val status: LiveData<TodoApiStatus>
        get() = _status

    private val _todos = MutableLiveData<List<TodoModel>>()
    val todo: LiveData<List<TodoModel>>
        get() = _todos

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

        getTodoProperties(TodoApiFilter.SHOW_ALL)

    }


    private fun getTodoProperties(filter: TodoApiFilter) {


        coroutineScope.launch {
            _status.value = TodoApiStatus.LOADING
            var getTodosDeferred = TodoApi.retrofitService.getTodos()
            if (filter != TodoApiFilter.SHOW_ALL) {
                getTodosDeferred = TodoApi.retrofitService.getTodosFilter(filter.value)
            }
            try {
                /**
                 * Deferred nesnesindeki await () yönteminin çağrılması, değer hazır olduğunda ağ çağrısının sonucunu döndürür.
                 * Await () yöntemi engellenmez, bu nedenle Mars API hizmeti geçerli iş parçacığını engellemeden ağdan veri alır -
                 * bu önemlidir, çünkü UI iş parçacığının kapsamındayız.
                 * Görev tamamlandığında, kodunuz kaldığı yerden çalışmaya devam eder. Await yöntemi veriyi beklerken MainThread serbesttir.
                 */
                _status.value = TodoApiStatus.LOADING
                val listTodos = getTodosDeferred.await()
                _status.value = TodoApiStatus.DONE
                _todos.value = listTodos
            } catch (e: Exception) {

                _todos.value = ArrayList()
                _status.value = TodoApiStatus.ERROR

            }
        }


    }

    // Eğer filter tetiklendiyse.
    fun updateListFilter(filter: TodoApiFilter) {
        getTodoProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
