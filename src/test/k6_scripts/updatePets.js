import http from 'k6/http';
import { check, sleep } from 'k6';

export default function () {
    const url = 'http://localhost:8080/api/v3/pet';
    const payload = JSON.stringify({
    id: 1, // Assuming pet ID 1 exists
    status: 'sold',
    });

    const params = {
    headers: {
    'Content-Type': 'application/json',
    },
    };

    const res = http.put(url, payload, params);

    check(res, {
    'is status 200': (r) => r.status === 200,
    });

    sleep(1);
}
