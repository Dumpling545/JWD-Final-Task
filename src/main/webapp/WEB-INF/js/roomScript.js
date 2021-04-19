$("#checkModal").on('show.bs.modal', function () {
  $("#checkinSpan").text($("#checkin").val());
  $("#checkoutSpan").text($("#checkout").val());
  $("#durationSpan").text("" + gDuration);
  var total = gDuration * gRoomPrice;
  $("#paymentAmountSpan").text("" + total);
});
$('#sender').click(function () {
  $.post("Controller", { command: "book", checkin: $("#checkin").val(), checkout: $("#checkout").val(), roomId: gId })
    .done(function (data, textStatus, jqXHR) {
      $("#successCheckinSpan").text(data.checkin);
      $("#successCheckoutSpan").text(data.checkout);
      $("#successPaymentAmountSpan").text(data.payment);
      $("#successPaymenTokenSpan").text(data.token);
      $("#successModal").modal();
    }).fail(function (jqXHR, textStatus, error) {
      if(jqXHR.status == 401){
        $("#errorModalHeader").text(gBookUnauthorizedHeader);
        $("#errorModalMessage").text(gBookUnauthorizedMessage);
      } else if(jqXHR.status == 409){
        $("#errorModalHeader").text(gBookDatesOccupiedHeader);
        $("#errorModalMessage").text(gBookDatesOccupiedMessage);
      } else {
        $("#errorModalHeader").text(gServerErrorHeader);
        $("#errorModalMessage").text(gServerErrorMessage);
      }
      $("#errorModal").modal();
    });
});
