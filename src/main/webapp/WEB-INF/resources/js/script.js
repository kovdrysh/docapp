// $(document).ready(function () {
//     $('#edit-folder').each(function () {
//         console.log($(this).data('id'));
//         $.ajax({
//             type: "GET",
//             url: "/check",
//             data: {
//                 "id": $('this').data('id'),
//                 "action": "edit",
//                 "isFolder": true
//             },
//             success: function (data) {
//                 console.log(data);
//                 if (!data){
//                     $(this).prop('disabled');
//                     $(this).css('color','rgba(0, 0, 0, 0.57)');
//                 }
//             }
//         })
//     });
//     $('#delete-folder').each(function () {
//         console.log($(this).data('id'));
//         $.ajax({
//             type: "GET",
//             url: "/check",
//             data: {
//                 "id": $(this).data('id'),
//                 "action": "delete",
//                 "isFolder": true
//             },
//             success: function (data) {
//                 console.log(data);
//                 if (!data){
//                     $(this).prop('disabled');
//                     $(this).css('color','rgba(0, 0, 0, 0.57)');
//                 }
//             }
//         })
//     })
// })
//
// // $("#folder-form").submit(function(e) {
// //
// //     var url = "/check"; // the script where you handle the form input.
// //
// //     $.ajax({
// //         type: "GET",
// //         url: url,
// //         data: {
// //             "id": $('#folder-name').val(),
// //             "action": "edit",
// //             "isFolder": true
// //         },
// //         success: function(data)
// //         {
// //             console.log(data);
// //             if (data){
// //                 $.ajax({
// //                     type: "POST",
// //                     url: "/addFolder",
// //                     data: $("#folder-form").serialize(),
// //                     async: false
// //                 })
// //             }
// //         }
// //     });
// //
// //     e.preventDefault(); // avoid to execute the actual submit of the form.
// // });