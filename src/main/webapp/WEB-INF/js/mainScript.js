$(document).ready(function () {
    $("#dateRangeFormGroup").removeClass("hidden");
    $("#checkinFormGroup").addClass("hidden");
    $("#checkoutFormGroup").addClass("hidden");
});
var gDuration = 0;
$(function () {
    var startDate = moment().clone().startOf('day');
    var endDate = moment({ day: 1 }).clone().startOf('day');
    if (start !== "" && end !== "") {
        startDate = moment(start);
        endDate = moment(end);
        gDuration = endDate.diff(startDate, 'days');
        $("#textdate").val(startDate.format('L') + ' - ' + endDate.format('L'));
    }
    $('input[name="textdate"]').daterangepicker({
        "minSpan": {
            "days": 1
        },
        "drops": "left",
        "minDate": moment().clone().startOf('day'),
        "startDate": startDate,
        "endDate": endDate,
        autoUpdateInput: false
    }, function (start, end, label) { });
    $('input[name="textdate"]').on('apply.daterangepicker', function (ev, picker) {
        document.getElementById("checkin").valueAsDate = picker.startDate.endOf('day').toDate();
        document.getElementById("checkout").valueAsDate = picker.endDate.toDate();
        var st = picker.startDate.clone().endOf('day');
        gDuration = picker.endDate.diff(st, 'days');
        $(this).val(picker.startDate.format('L') + ' - ' + picker.endDate.format('L'));
    });

    $('input[name="textdate"]').on('cancel.daterangepicker', function (ev, picker) {
        $("#checkin").val("");
        $("#checkout").val("");
        $(this).val('');
    });
});
