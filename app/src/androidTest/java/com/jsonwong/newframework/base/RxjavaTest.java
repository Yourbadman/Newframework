package com.jsonwong.newframework.base;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Authors：Administrator on 2016/4/9 14:16
 */
public class RxjavaTest {

//
//    public static final void main(final String[] args) throws IOException {
//        final OkHttpClient client = new OkHttpClient();
//        final Request req = new Request.Builder().url("http://www.yahoo.co.jp")
//                .build();
//        Observable.from(client.newCall(req).execute().body().string().split("\n"))
//                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(20)))
//                .filter(line  -> {return line.contains("<img");})
//                .map((str)    -> {return extractMatches(str, "src=\"(.+?)\"");})
//                .filter((opt) -> {return opt.isPresent();})
//                .map((opt)    -> {return opt.get();})
//                .distinct()
//                .subscribe(
//                        (opt) -> {System.out.println(Thread.currentThread().getName() + " " + opt);},
//                        (e)   -> {e.printStackTrace();},
//                        ()    -> {System.out.println("completed");}
//                );
//    }


    public static String extractMatches(final String target, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(target);
        return (matcher.find()) ? matcher.group(1) : "";
    }
}
