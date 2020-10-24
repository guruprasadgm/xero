# xero-accounting #xeroexcercise

Note: * All the tests were created and tested on a Mac OS Mojave version 10.14.6
      * Test automation solution is created using java 8, gradle, Junit
      * Trail test account was created and is provided in the solution
     

pre-requesites
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

1. Clone the solution from following repo: https://github.com/guruprasadgm/xero

2. Chrome browser Version 85 (Official Build) (64-bit) should be installed.
      ChromeDriver 74.0.3729.6 (already uploaded with the solution in case not able to download should be downloaded separately and location to be provided as mentioned below)

3. Firefox 80 (64-bit) should be installed.
     Gecko driver version v0.27.0  (already uploaded with the solution in case not able to download should be downloaded separately and location to be provided as mentioned below)

4. need to have java 8 installed on the execution machine 


Steps to execute the test: Run the below commands in terminal (mac)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

1.  mac: ./gradlew clean
    
    win: gradlew clean


2. mac:  ./gradlew build
   
   win:   gradlew build

3. To run tests on Chrome:
    
    mac: ./gradlew test -Drun.test=true -DbrowserName="chrome" -Dwebdriver.chrome.driver="./src/test/resources/chromedriver" -DtargetAppURL="https://www.xero.com/nz/" --info
    
    win: gradlew test -Drun.test=true -DbrowserName="chrome" -Dwebdriver.chrome.driver="src/test/resources/chromedriverwin.exe" -DtargetAppURL="https://www.xero.com/nz/" --info
     
     (Note: in case the chrome driver was downloaded separately the location path should be provided for -Dwebdriver.chrome.driver)


4. To run the tests on Firefox:
   
   mac: ./gradlew test -Drun.test=true -DbrowserName="firefox" -Dwebdriver.gecko.driver="./src/test/resources/geckodriver" -DtargetAppURL="https://www.xero.com/nz/" --info
   
   win: gradlew test -Drun.test=true -DbrowserName="firefox" -Dwebdriver.gecko.driver="src/test/resources/geckodriverwin.exe" -DtargetAppURL="https://www.xero.com/nz/" --info
    
    (Note: in case the gecko driver was downloaded separately the location path should be provided for -Dwebdriver.gecko.driver)
