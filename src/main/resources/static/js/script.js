function preventDefaults(e) {
    e.preventDefault();
    e.stopPropagation();
}

function highlight(e) {
    e.currentTarget.classList.add('highlight');
}

function unhighlight(e) {
    e.currentTarget.classList.remove('highlight');
}

function handleDrop(e) {
    e.preventDefault();
    e.stopPropagation();

    var files = e.dataTransfer.files;
    if (files.length > 0) {
        var form = new FormData();
        form.append('file', files[0]);

        var xhr = new XMLHttpRequest();
        xhr.open('POST', e.currentTarget.dataset.url, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('File uploaded successfully!');
            } else {
                console.error('Failed to upload the file.');
            }
        };
        xhr.send(form);
    }
}

var dropArea = document.getElementById('dropArea');
dropArea.addEventListener('dragenter', preventDefaults, false);
dropArea.addEventListener('dragover', preventDefaults, false);
dropArea.addEventListener('dragleave', preventDefaults, false);
dropArea.addEventListener('drop', preventDefaults, false);
dropArea.addEventListener('dragenter', highlight, false);
dropArea.addEventListener('dragover', highlight, false);
dropArea.addEventListener('dragleave', unhighlight, false);
dropArea.addEventListener('drop', unhighlight, false);
