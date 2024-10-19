import http from 'k6/http';
import { check, sleep } from 'k6';

export default function () {
    const url = 'http://localhost:8080/api/v3/pet';
    const payload = JSON.stringify({
        id: Math.floor(Math.random() * 1000), // Random ID for demonstration
        name: 'Fluffy',
        status: 'available',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'is status 200': (r) => r.status === 200,
    });

    sleep(1);
}
