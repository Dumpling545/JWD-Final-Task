$("#checkModal").on('show.bs.modal', function(){
    $("#checkinSpan").text($("#checkin").val());
    $("#checkoutSpan").text($("#checkout").val());
    $("#durationSpan").text("" + gDuration);
    var total = gDuration*gRoomPrice;
    $("#paymentAmountSpan").text("" + total);
  });
$('#sender').click(function() {
  $.post("Controller", {command: "book", checkin: $("#checkin").val(), checkout: $("#checkout").val(), roomId: gId}, function(data, status){
    alert(data);
  });
});
