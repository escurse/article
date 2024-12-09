const $form = document.getElementById('form');
const $table = document.querySelector('#table');
const $tbody = $table.querySelector(':scope > tbody')

const loadComments = () => {
    const xhr = new XMLHttpRequest();
    const url = new URL(location.href);
    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) {
            return;
        }
        if (xhr.status < 200 || xhr.status >= 300) {
            alert('오류 발생');
            return;
        }
        const $tr = Array.from(new DOMParser().parseFromString(xhr.responseText, 'text/html').querySelectorAll('.comment'));
        $tr.forEach((x) => {
            $tbody.append(x);
        });
    };
    xhr.open('GET', `../comment/?articleIndex=${url.searchParams.get('index')}`);
    xhr.send();
}

loadComments();

$form.onsubmit = (e) => {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    const url = new URL(location.href);
    const formData = new FormData();
    formData.append('articleIndex', url.searchParams.get('index'));
    formData.append('writer', $form['commentWriter'].value);
    formData.append('content', $form['commentContent'].value);
    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) {
            return;
        }
        if (xhr.status < 200 || xhr.status >= 300) {
            alert('오류 발생');
            return;
        }
        const response = JSON.parse(xhr.responseText);
        if (response['result'] === "success") {
            $tbody.innerHTML = '';
            loadComments();
        } else {
            alert("댓글 작성에 실패하였습니다.");
        }
    };
    xhr.open('POST', '../../comment/write');
    xhr.send(formData);
}
