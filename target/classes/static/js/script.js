var dropArea = document.getElementById('dropArea');
var uploadForm = document.getElementById('uploadForm');
var currentSVG = "";
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

    console.log(currentSVG + " DROPPED ONTO FRIDGE!")

    // Get the item file name from the "src" attribute
    var itemName = currentSVG.split(".").shift();
    itemsInFridge.push(itemName);
}

// Add event listener to each SVG item
var svgItems = document.querySelectorAll(".svg-item");
svgItems.forEach(function(svgItem) {
    svgItem.addEventListener("mousedown", function(e) {
        // Get the SVG file name when the SVG item is clicked down
        var svgFileName = e.target.src.split("/").pop();
        currentSVG = svgFileName;
    });
});


function changeOpacity(svgItem) {
  svgItem.style.opacity = 0.5; // Set the opacity to the desired value when the SVG item is clicked
  // You can also add additional logic here if needed
}