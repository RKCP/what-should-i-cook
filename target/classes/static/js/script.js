var dropArea = document.getElementById('dropArea');
var uploadForm = document.getElementById('uploadForm');
var currentSVGName = "";
var selectedSVG;
var itemsInFridge = [];

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

    console.log(currentSVGName + " DROPPED ONTO FRIDGE!")

    // Get the item file name from the "src" attribute
    var itemName = currentSVGName.split(".").shift();
    itemsInFridge.push(itemName);
    selectedSVG.style.opacity = 0.5;
}

// Add event listener to each SVG item
var svgItems = document.querySelectorAll(".svg-item");
svgItems.forEach(function(svgItem) {
    svgItem.addEventListener("mousedown", function(e) {
        // Get the SVG file name when the SVG item is clicked down
        var svgFileName = e.target.src.split("/").pop();
        currentSVGName = svgFileName;
    });
});

document.getElementById('shakingImage').addEventListener('click', function() {
    this.classList.add('shake');

    // Remove the 'shake' class after the animation completes
    this.addEventListener('animationend', function() {
        this.classList.remove('shake');
    });
});


function getSVGElement(svgItem) {
    selectedSVG = svgItem;
  //svgItem.style.opacity = 0.5; // Set the opacity to the desired value when the SVG item is clicked
  // You can also add additional logic here if needed
}