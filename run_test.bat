cls

rem Create partners
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"Alex Alekhin\", \"partnerType\":\"COMPANY\"}" http://localhost:8080/partners
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"Slava Petukhov\", \"partnerType\":\"SOLE_PROPRIETORSHIP\"}" http://localhost:8080/partners
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"N P\", \"partnerType\":\"NATURAL_PERSON\"}" http://localhost:8080/partners

rem Read first partner
curl http://localhost:8080/partners/1

rem Update first partner
curl -X PUT -H "Content-Type:application/json" -d "{\"name\":\"AleAlex\", \"partnerType\":\"COMPANY\"}" http://localhost:8080/partners/1

rem Delete first partner
rem curl -i -H "Accept: application/json" -X DELETE http://localhost:8080/partners/1

rem Read partners
curl http://localhost:8080/partners



rem Create addresses
rem curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"country\", \"index\":\"index\", \"state\":\"state\", \"province\":\"province\", \"city\":\"city\", \"streetAddress\":\"streetAddress\"}" http://localhost:8080/addresses
rem curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"country1\", \"index\":\"index\", \"state\":\"state\", \"province\":\"province\", \"city\":\"city\", \"streetAddress\":\"streetAddress\"}" http://localhost:8080/addresses

rem Read addresses
curl http://localhost:8080/addresses
                                                                                                                                     

rem Create partner's addresses
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"Regi\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/REGISTERED
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"Real\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/REAL
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"Logi\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/LOGISTIC
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"Corr\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/CORRESPONDENCE

curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"USA\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/2/addresses/REGISTERED
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"USA\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/2/addresses/REAL

curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"RU\", \"index\":\"index3\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/3/addresses/REGISTERED
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"RU\", \"index\":\"index3\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/3/addresses/REAL
curl -i -X POST -H "Content-Type:application/json" -d "{\"country\":\"Logi\", \"index\":\"index1\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/3/addresses/LOGISTIC


rem Update partner's addresses
rem curl -X PUT -H "Content-Type:application/json" -d "{\"country\":\"Real\", \"index\":\"index3\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/REAL
curl -X PUT -H "Content-Type:application/json" -d "{\"country\":\"Logi Update 1\", \"index\":\"index3\", \"state\":\"state1\", \"province\":\"province1\", \"city\":\"city1\", \"streetAddress\":\"streetAddress1\"}" http://localhost:8080/partners/1/addresses/LOGISTIC/3

rem Read partner's addresses
curl http://localhost:8080/partners/1/addresses
curl http://localhost:8080/addresseslinks

curl http://localhost:8080/partners/1/addresses/REGISTERED
curl http://localhost:8080/partners/1/addresses/REAL
curl http://localhost:8080/partners/1/addresses/LOGISTIC
curl http://localhost:8080/partners/1/addresses/CORRESPONDENCE

rem Delete partner's addresses
curl -X DELETE http://localhost:8080/partners/1/addresses/REAL
curl -X DELETE http://localhost:8080/partners/1/addresses/REAL/0
curl -X DELETE http://localhost:8080/partners/1/addresses/CORRESPONDENCE/4

rem Get partner's contact info
curl http://localhost:8080/partners/2/contactinfo
rem Update partner's contact info
curl -i -X POST -H "Content-Type:application/json" -d "{\"contactPerson\": \"Alex Alekhin 3\", \"contactType\": \"EMAIL\", \"workingHours\": { \"startHour\": 12, \"endHour\": 18 }, \"contactInfo\": \"alex.alekhin@gmail.com\"}" http://localhost:8080/partners/3/contactinfo
rem Update partner's contact info
curl -X PUT -H "Content-Type:application/json" -d "{\"contactPerson\": \"Alex Alekhin\", \"contactType\": \"EMAIL\", \"workingHours\": { \"startHour\": 6, \"endHour\": 18 }, \"contactInfo\": \"alex.alekhin@gmail.com\"}" http://localhost:8080/partners/1/contactinfo
rem Update partner's contact info
curl -X DELETE http://localhost:8080/partners/3/contactinfo

rem Print report
curl http://localhost:8080/report > report.csv