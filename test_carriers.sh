#!/usr/bin/env bash
set -euo pipefail

BASE="http://localhost:8080"
JSON="Content-Type: application/json"

echo "== Carriers: CREATE =="
CID=$(curl -sS -X POST "$BASE/api/v1/carriers" -H "$JSON" \
  -d '{"name":"Ege Lojistik","contact":"info@ege.com","locationId":1}' | jq -r '.id')
echo "created id=$CID"

echo "== Carriers: GET =="
curl -sS "$BASE/api/v1/carriers/$CID" | jq '. | {id, name, contact, locationId}'

echo "== Carriers: LIST (q filter & paging) =="
curl -sS "$BASE/api/v1/carriers?q=loj&page=0&size=10&sort=name,asc" \
  | jq '{totalElements, size, content:[.content[].name]}'

echo "== Carriers: UPDATE =="
curl -sS -X PUT "$BASE/api/v1/carriers/$CID" -H "$JSON" \
  -d '{"name":"Ege Lojistik AŞ","contact":"destek@ege.com","locationId":1}' \
  | jq '. | {id, name, contact}'

echo "== Carriers: VALIDATION (400 beklenir) =="
code=$(curl -sS -o /tmp/val.json -w "%{http_code}" -X POST "$BASE/api/v1/carriers" -H "$JSON" \
  -d '{"name":"","contact":"x"}')
echo "HTTP $code"; test "$code" = "400" && jq '.' /tmp/val.json || { echo "Validation test FAILED"; exit 1; }

echo "== Carriers: DELETE =="
HTTP_DEL=$(curl -sS -o /dev/null -w "%{http_code}" -X DELETE "$BASE/api/v1/carriers/$CID")
echo "HTTP $HTTP_DEL"
test "$HTTP_DEL" = "204" || { echo "Delete failed"; exit 1; }

echo "== Carriers: GET after DELETE (404 beklenir) =="
code=$(curl -sS -o /tmp/nf.json -w "%{http_code}" "$BASE/api/v1/carriers/$CID")
echo "HTTP $code"
test "$code" = "404" && jq '.' /tmp/nf.json || { echo "NotFound test FAILED"; exit 1; }

echo "✔ Smoke suite PASSED"
