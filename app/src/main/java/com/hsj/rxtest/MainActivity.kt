package com.hsj.rxtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observableWithCreate()
    }

    /**
     * onNext() : 하나의 소스 Observable에서 Observer까지 한 번에 하나씩 순차적으로 데이터를 발행한다.
     * onComplete() : 데이터 발행이 끝났음을 알리는 완료 이벤트를 Observer에 전달하여 onNext()를 더 호출하지 않음을 나타낸다.
     * onError() : 오류가 발생했음을 Observer에 전달한다.
     */
    private fun observableWithCreate(){
//        val observable: Observable<String> = Observable.create { emitter ->
//            emitter.onNext("Hello")
//            emitter.onNext("rx")
//            emitter.onComplete()
//        }
//        observable.subscribe(System.out::println)

        val source = Observable.create { emitter: ObservableEmitter<String?> ->
            emitter.onNext("Hello")
            emitter.onError(Throwable())
            emitter.onNext("Yena")
        }
        source.subscribe(
            { x: String? -> println(x) }
        ) { throwable: Throwable? ->
            println(
                "Good bye"
            )
        }
    }
}