//String variable locationServicesError is required to work correctly!
$(document).ready(function () {
    var key = 'pk.c46d63a472dc505f81781cfbd989a154';
    var streets = L.tileLayer.Unwired({ key: key, scheme: "streets" });
    var map = L.map('map', {
        center: [$("#lat").val(), $("#lng").val()],
        zoom: 1,
        scrollWheelZoom: false,
        layers: [streets]
    });
    L.control.scale().addTo(map);
    L.control.layers({
        "Streets": streets
    }).addTo(map);
    var marker = L.marker([$("#lat").val(), $("#lng").val()], { draggable: true })
        .addTo(map);
    marker.on('dragend', function (event) {
        var position = marker.getLatLng();

        marker.setLatLng(position, {
            draggable: 'true'
        });

    });
    $("#getAddress").click(function () {
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "https://us1.locationiq.com/v1/reverse.php?key=" +
                key + "&lat=" + marker.getLatLng().lat + "&lon=" +
                marker.getLatLng().lng + "&format=json",
            "method": "GET"
        };
        $.ajax(settings).done(function (response) {
            $("#cachedAddress").val(response.display_name);
            $("#lat").val(marker.getLatLng().lat);
            $("#lng").val(marker.getLatLng().lng);
        }).fail(function () {
            alert(locationServicesError);
        });
    });
});