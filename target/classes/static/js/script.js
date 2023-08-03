var dropArea = document.getElementById('dropArea');
var uploadForm = document.getElementById('uploadForm');

// Prevent default behavior when files are dragged and dropped onto the drop area
dropArea.addEventListener('dragenter', preventDefault, false);
dropArea.addEventListener('dragleave', preventDefault, false);
dropArea.addEventListener('dragover', preventDefault, false);
dropArea.addEventListener('drop', handleDrop, false);

// Open file dialog when the drop area is clicked
dropArea.addEventListener('click', function() {
    console.log("FRIDGE CLICKED")
});

function preventDefault(e) {
    e.preventDefault();
    e.stopPropagation();
}

function handleDrop(e) {
    preventDefault(e);
    var dataTransfer = e.dataTransfer;
    var itemsInFridge = [];

    console.log("ITEM DROPPED ONTO FRIDGE!")

    // Process the dropped files or perform further actions
    // handleFiles(files);
}

function handleFiles(files) {
    if (files.length > 0) {
        var formData = new FormData();
        formData.append('fileInput', files[0]);

        // Submit the form with the selected file
        uploadForm.submit(); // uploadForm refers to a DOM element with the id uploadForm
    }
}

function changeOpacity(svgItem) {
  svgItem.style.opacity = 0.5; // Set the opacity to the desired value when the SVG item is clicked
  // You can also add additional logic here if needed
}