const BASE_AUTH_API = `${process.env.NEXT_PUBLIC_API_URL}/api/auth`;

const REQUEST_CODE_API = `${BASE_AUTH_API}/request-code`;
const VERIFY_CODE_API = `${BASE_AUTH_API}/verify-code`;

export { REQUEST_CODE_API, VERIFY_CODE_API };
