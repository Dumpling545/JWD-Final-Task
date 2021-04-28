//double variables latitude, longitude are required to work correctly!
var map = null;
$(document).ready(function () {
    var key = 'pk.c46d63a472dc505f81781cfbd989a154';
    var streets = L.tileLayer.Unwired({ key: key, scheme: "streets" });
    map = L.map('map', {
        center: [latitude, longitude],
        zoom: 14,
        scrollWheelZoom: false,
        layers: [streets]
    });
    L.control.scale().addTo(map);
    L.control.layers({
        "Streets": streets
    }).addTo(map);
    var marker = L.marker([latitude, longitude]).addTo(map);
});
$('#mapModal').on('shown.bs.modal', function(){
    map.invalidateSize();
});