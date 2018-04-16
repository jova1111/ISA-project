<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            #map {
                width: 300px;
                height: 300px;
            }
        </style>
    </head>

    <body style="margin: 15px;">
        <c:import url="_navbar.jsp"></c:import>

        <div>
            <h3>${ institution.name }</h3>
            <p>Adresa: ${ institution.address }</p>
            <p>Ocena: ${ institution.getAverageRating() }</p>
        </div>

        <div id="map"></div>
        <script>
            function initMap() {
                $.get('https://maps.googleapis.com/maps/api/geocode/json?address=${ institution.address }', function(data){

                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 17,
                        center: data.results[0].geometry.location
                    });
                    var marker = new google.maps.Marker({
                        position: data.results[0].geometry.location,
                        map: map
                    });
                })

            }
        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGntqueG2mBLJzfEuOAHNluE8yQSVS5JA&callback=initMap">
        </script>

        <c:if test="${ not empty loggedUser }">Ulogovan</c:if>
    </body>

</html>