// Function to show login card
function showLoginCard() {
    const loadingContainer = $('#loading-container');
    const loginCard = $('#login-card');

    loadingContainer.css('opacity', 0);
    loadingContainer.css('visibility', 'hidden');

    loginCard.css('display', 'block');
}

// Use jQuery Ajax to simulate backend response
function fetchBackendResponseAndShowCard() {
    $.ajax({
        url: 'api/login', // Replace with your actual backend API endpoint
        method: 'GET',
        dataType: 'json', // Adjust the dataType based on your backend response format
        success: function (response) {
            // Simulate successful login (change this based on your backend response)
            if (response && response.success) {
                showLoginCard();
            }
        },
        error: function (error) {
            console.error('An error occurred while fetching the backend response:', error);
        }
    });
}

// Call the function to fetch backend response and show the login card
fetchBackendResponseAndShowCard();