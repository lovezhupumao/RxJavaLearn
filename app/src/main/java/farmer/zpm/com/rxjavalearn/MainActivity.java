package farmer.zpm.com.rxjavalearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func0;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private Subscription just;
    private Subscription range;
    private Subscription interval;
    private Subscription timer_2;
    private Subscription timer_3;
    private Subscription filter;
    private Subscription repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rxjava_just();
        Rxjava_repeat();
        Observable.defer(() -> Observable.just("defer")).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("repeat------onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("repeat---------onError",e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.e("repeat---------OnNext","="+s);
            }
        });
        Rxjava_range();
        Rxjava_interval();
        Rxjava_timer_2();
        Rxjava_timer_3();
        Rxjava_filter();

        lambda();
    }

    private void Rxjava_repeat() {
        repeat=  Observable.just(1,3,4)
                .repeat(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("repeat------onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("repeat---------onError",e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("repeat---------OnNext","="+integer);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        just.unsubscribe();
        range.unsubscribe();
        interval.unsubscribe();
        timer_2.unsubscribe();
        timer_3.unsubscribe();
        filter.unsubscribe();
        repeat.unsubscribe();
    }

    private void Rxjava_filter() {
        List<String> list=new ArrayList<String>();
        list.add("on");
        list.add("one");
        list.add("two");
        list.add("owe");
      filter=  Observable.from(list).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.startsWith("o");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("filter----onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("filter---onError",e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i("filter----OnNext","="+s);
            }
        });
    }

    private void Rxjava_timer_3() {
       timer_3= Observable.timer(3, 3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Long number) {
                        Log.d("RXJAVA", "I say " + number);
                    }
                });
    }

    private void Rxjava_timer_2() {
       timer_2= Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.e("timer----onCompleted","onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("timer---onError",e.getMessage());
                    }
                    @Override
                    public void onNext(Long number) {
                        Log.e("timer----OnNext","="+number);
                    }
                });
    }

    private void Rxjava_interval() {
        interval=  Observable.just(10).interval(3, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Log.e("interval----onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("interval---onError",e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                Log.e("interval----OnNext","="+aLong);
            }
        });

    }

    private void Rxjava_range() {
       range= Observable.range(10,3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("range-------onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("range----------onError",e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("range----------OnNext","="+integer);
            }
        });
    }

    private void lambda() {
        TextView textView=(TextView)findViewById(R.id.text);
        textView.setOnClickListener(view -> Log.e("hello","-----------1"));
    }

    protected void Rxjava_just() {
      just=  Observable.just(1,3,4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("Just--------onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Just-----------onError",e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("Just-----------OnNext","="+integer);
            }
        });
    }

}
