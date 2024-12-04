// const $form = document.getElementById('form');
// $form.onsubmit = (e) => {
//     e.preventDefault();
//     const xhr = new XMLHttpRequest();
//     const formData = new FormData();
//     formData.append('writer', $form['writer'].value);
//     formData.append('title', $form['title'].value);
//     formData.append('content', $form['content'].value);
//     xhr.onreadystatechange = () => {
//         if (xhr.readyState !== XMLHttpRequest.DONE) {
//             return;
//         }
//         if (xhr.status < 200 || xhr.status >= 300) {
//             alert('오류 발생');
//             return;
//         }
//         const response = JSON.parse(xhr.responseText);
//         if (response['result'] === "success") {
//             location.href = `./read?index=${response['index']}`;
//         } else {
//             alert("게시글 작성에 실패하였습니다.");
//             history.back();
//         }
//     };
//     xhr.open('POST', location.href);
//     xhr.send(formData);
// }