$('document').on('show.bs.modal','#confirm-delete', function(e) {
    console.log("haha")
    if ($(e.relatedTarget).data('folder'))
        $(this).find('.modal-body').html('Are you sure to delete this folder with its files?');
    else
        $(this).find('.modal-body').html('Are you sure to delete this file?');
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});