function draw(diameter) {
    const canvas = document.getElementById('circle');
    const ctx = canvas.getContext('2d');
    const X = canvas.width / 2;
    const Y = canvas.height / 2;
    ctx.clearRect(0, 0, X * 2, Y * 2)
    if (canvas.getContext) {
        const R = diameter / 2;
        ctx.lineWidth = 3;
        ctx.strokeStyle = 'red';

        ctx.beginPath();
        ctx.arc(X, Y, R, 0, 2 * Math.PI, false);
        ctx.fillStyle = 'red';
        ctx.fill();

        ctx.font = '15px Arial';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillStyle = 'yellow';

        ctx.fillText(diameter + ' px', X, Y);
    }
}