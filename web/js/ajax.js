$(document).ready(function () {

    $(function () {
        $("#sortable").sortable();
        $("#sortable").disableSelection();
    });

    $.ajax({
        url: '/getList',
        dataType: 'json',
        success: function (data, textStatus) {
            $.each(data, function (i, val) {
                var textString = jQuery.parseJSON(val);
                var str = '<li>' + textString.id + ' : ' + textString.info + '</li>';
                $('#sortable').append(str);
            });
        }
    });

    $('#if').click(function () {
        this.value = '';
    })

    $('#ta').click(function () {
        this.value = '';
    })

    $('#1').click(function () {
        $textFromArea = $('#ta').val();
        if ($textFromArea.length == 0) {
            alert('Cannot add empty string');
        } else if ($textFromArea == 'Type your text here...') {
            alert('Cannot add default string');
        } else {
            var str = '<li>' + $textFromArea + '</li>';
            $('#sortable').append(str);
            sentToServer();
        }
    })

    $('#2').click(function () {
        //var jqxhr = $.post('/save', function () {
        //})
        var req = "";
        $('#sortable li').each(function (i, val) {
            req += $(val).text() + '\n';
        });
        $.ajax({
            url: '/saveAll',
            method: 'POST',
            data: 'infoStrings=' + req,
            success: function (data) {
            }
        });
    })
});

function sentToServer() {
    var textFromArea = $('#ta').val();
    $.ajax({
        url: '/addString',
        method: 'POST',
        data: 'TextString=' + textFromArea,
        success: function (data) {
        }
    });
}

