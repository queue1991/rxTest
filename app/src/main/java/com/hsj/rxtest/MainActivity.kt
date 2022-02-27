package com.hsj.rxtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.functions.Consumer

/**
 * onNext() : 하나의 소스 Observable에서 Observer까지 한 번에 하나씩 순차적으로 데이터를 발행한다.
 * onComplete() : 데이터 발행이 끝났음을 알리는 완료 이벤트를 Observer에 전달하여 onNext()를 더 호출하지 않음을 나타낸다.
 * onError() : 오류가 발생했음을 Observer에 전달한다.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observableWithFromIterable()
    }


    /**
     * Observable.create()를 사용하면 Emitter를 이용하여 직접 아이템을 발행하고,
     * 아이템 발행의 완료나 오류(Complete/Error)의 알림을 직접 설정할 수 있다.
     */
    private fun observableWithCreate(){
//        val observable: Observable<String> = Observable.create { emitter ->
//            emitter.onNext("Hello")
//            emitter.onNext("rx")
//            emitter.onComplete()
//        }
//        observable.subscribe(System.out::println)

        val observable = Observable.create { emitter: ObservableEmitter<String?> ->
            emitter.onNext("Hello")
            emitter.onError(Throwable())
            emitter.onNext("Yena")
        }
        observable.subscribe(
            { x: String? -> println(x) }
        ) { throwable: Throwable? ->
            println(
                "Good bye"
            )
        }
    }

    /**
     * just()는 해당 아이템을 그대로 발행하는 Observable을 생성해준다.
     * just() 연산자의 인자로 넣은 아이템을 차례로 발행하며,
     * 한 개의 아이템을 넣을 수도 있고, 타입이 같은 여러 아이템을 넣을 수도 있다.
     */
    private fun observableWithJust(){
        val observable : Observable<String> = Observable.just("Hello","just")
        observable.subscribe(System.out::println)
    }

    /**
     * 단일 데이터가 아닌경우
     * fromXXX()함수
     */
    private fun observableWithFromArray() {
        val itemArray = arrayOf("Morning", "Afternoon", "Evening")
        val observable = Observable.fromArray(*itemArray)
        observable.subscribe(System.out::println)
    }

    /**
     * 단일 데이터가 아닌경우
     * fromXXX()함수
     */
    private fun observableWithFromIterable() {
        val itemArray = mutableListOf("Alpha", "Beta", "Gamma")
        val observable = Observable.fromArray(itemArray)
        observable.subscribe(System.out::println)
    }
}