javaeeサンプル
=======================================

wildflyをダウンロードして解凍先を以下のファイルに設定する

src/test/resources/arquillian.xml

※ bin\add-user.batでwildflyに管理ユーザーの登録が必要かも？

以下でテストを実行

  gradlew test

テストはgroovy + spockを使用。

以下でwarファイルが出来るので適当にデプロイすれば動くかと。

  gradlew war

課題
=============================================

以下を使ってみたが数回に1回しかCDIが成功しない。
原因不明。後回し。

arquillian-testrunner-spock
https://github.com/arquillian/arquillian-testrunner-spock


参考サイト
=============================================

wildfly
http://www.wildfly.org/

spock
http://spockframework.org/

グロブ
http://typea.info/blg/glob/2014/04/java-ee-7-1-wildfly-jboss-tools-ear-arquillian.html

きしだのはてな
http://d.hatena.ne.jp/nowokay/20131213

