$("folder-form").submit(function(e) {

    var url = "/check"; // the script where you handle the form input.

    $.ajax({
        type: "GET",
        url: url,
        data: {

        },
        success: function(data)
        {
            alert(data); // show response from the php script.
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});