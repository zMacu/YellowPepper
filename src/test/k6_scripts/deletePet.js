import http from 'k6/http';
import { check, sleep } from 'k6';

export default function () {
    const petId = 1; // Assuming pet ID 1 exists
    const res = http.del('http://localhost:8080/api/v3/pet/${petId}');

    check(res, {
        'is status 200': (r) => r.status === 200,
    });

    sleep(1);
}
