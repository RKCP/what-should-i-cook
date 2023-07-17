var dropArea = document.getElementById('dropArea');
var fileInput = document.getElementById('imageFile');
var uploadForm = document.getElementById('uploadForm');

// Prevent default behavior when files are dragged and dropped onto the drop area
dropArea.addEventListener('dragenter', preventDefault, false);
dropArea.addEventListener('dragleave', preventDefault, false);
dropArea.addEventListener('dragover', preventDefault, false);
dropArea.addEventListener('drop', handleDrop, false);

// Open file dialog when the drop area is clicked
dropArea.addEventListener('click', function() {
    fileInput.click();
});

// Handle file selection
fileInput.addEventListener('change', handleFileSelection);

function preventDefault(e) {
    e.preventDefault();
    e.stopPropagation();
}

function handleDrop(e) {
    preventDefault(e);

    var files = e.dataTransfer.files;

    // Process the dropped files or perform further actions
    handleFiles(files);
}

function handleFileSelection(e) {
    var files = e.target.files;

    // Process the selected files or perform further actions
    handleFiles(files);
}

function handleFiles(files) {
    if (files.length > 0) {
        var formData = new FormData();
        formData.append('imageFile', files[0]);
        console.log("Inside handleFiles method")

        // Submit the form with the selected file
        uploadForm.submit(); // uploadForm refers to a DOM element with the id uploadForm
    }
}
