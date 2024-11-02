document.addEventListener('DOMContentLoaded', function () {
    fetch('/api/concerts')
        .then(response => response.json())
        .then(data => {
            const contentDiv = document.getElementById('content');
            data.forEach(concert => {
                const concertElement = document.createElement('div');
                concertElement.textContent = `Концерт: ${concert.name}, Дата: ${concert.dateTime}`;
                contentDiv.appendChild(concertElement);
            });
        })
        .catch(error => console.error('Ошибка:', error));
});
