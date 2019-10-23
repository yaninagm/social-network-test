service="localhost:8080"

color_green="\033[32m"
color_red="\033[31m"
color_none="\033[0m"

pass () {
  echo -e "${color_green}OK${color_none} $1 $2 is ${color_green}$3${color_none} as expected"
}

fail () {
  echo -e "${color_red}KO${color_none} $1 $2 is ${color_red}$4${color_none} but expected ${color_green}$3${color_none}"
  echo -e "${color_red}One check failed!${color_none}"
  exit 1
}

given_request () {
  request=$1
  response=$(curl -X ${request} -s -w \\n%{http_code}\\n%{content_type})
  request_exit_status=$?
  [[ ${request_exit_status} != 0 ]] \
  && fail "${request}" "curl exit status" "0" "${request_exit_status}"
  response_body=$(echo "${response}" | head -1)
  response_status_code=$(echo "${response}" | head -2 | tail -1)
  response_content_type=$(echo "${response}" | head -3 | tail -1)
}

response_status_code_should_be () {
  expected_response_status_code=$1
  [[ ! "${response_status_code}" =~ ^${expected_response_status_code}$ ]] \
  && fail "${request}" "response status code" "${expected_response_status_code}" "${response_status_code}"
  pass "${request}" "response status code" "${expected_response_status_code}"
}

response_body_should_be () {
  expected_response_body=$1
  [[ "${response_body}" != "${expected_response_body}" ]] \
  && fail "${request}" "response body" "${expected_response_body}" "${response_body}"
  pass "${request}" "response body" "${expected_response_body}"
}

response_content_type_should_be () {
  expected_response_content_type=$1
  [[ ! "${response_content_type}" =~ ^${expected_response_content_type} ]] \
  && fail "${request}" "response content-type" "${expected_response_content_type}" "${response_content_type}"
  pass "${request}" "response content-type" "${expected_response_content_type}"
}

given_request "POST $service/signup?username=john"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/signup?username=john_doe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12-45678 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j1234 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j1234567890123 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/signup?username=johnisaniceguy"
response_status_code_should_be 4..

given_request "POST -H X-Password:j12345678 $service/signup?username=johndoe"
response_status_code_should_be 2..
given_request "POST -H X-Password:j12345678 $service/signup?username=johndoe"
response_status_code_should_be 4..

given_request "POST -H X-Password:r3456789 $service/signup?username=roseanne"
response_status_code_should_be 2..
given_request "POST -H X-Password:p4567890 $service/signup?username=peter"
response_status_code_should_be 2..
given_request "POST -H X-Password:j5678901 $service/signup?username=jessica"
response_status_code_should_be 2..
given_request "POST -H X-Password:r0123456 $service/signup?username=robert"
response_status_code_should_be 2..

given_request "POST -H X-Password:j12345679 $service/friendship/request?usernameFrom=johndoe&usernameTo=samantha"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=samantha"
response_status_code_should_be 4..

given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=roseanne"
response_status_code_should_be 2..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=roseanne"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=peter"
response_status_code_should_be 2..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=jessica"
response_status_code_should_be 2..

given_request "POST -H X-Password:r3456789 $service/friendship/accept?usernameFrom=roseanne&usernameTo=johndoe"
response_status_code_should_be 2..
given_request "POST -H X-Password:r3456789 $service/friendship/accept?usernameFrom=roseanne&usernameTo=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:r3456780 $service/friendship/accept?usernameFrom=roseanne&usernameTo=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:r3456789 $service/friendship/accept?usernameFrom=roseanne&usernameTo=peter"
response_status_code_should_be 4..

given_request "POST -H X-Password:p4567890 $service/friendship/decline?usernameFrom=peter&usernameTo=johndoe"
response_status_code_should_be 2..
given_request "POST -H X-Password:p4567890 $service/friendship/decline?usernameFrom=peter&usernameTo=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:p4567891 $service/friendship/decline?usernameFrom=peter&usernameTo=jessica"
response_status_code_should_be 4..
given_request "POST -H X-Password:p4567890 $service/friendship/decline?usernameFrom=peter&usernameTo=jessica"
response_status_code_should_be 4..

given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=robert"
response_status_code_should_be 2..
given_request "POST -H X-Password:r0123456 $service/friendship/decline?usernameFrom=robert&usernameTo=johndoe"
response_status_code_should_be 2..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=robert"
response_status_code_should_be 2..
given_request "POST -H X-Password:r0123456 $service/friendship/accept?usernameFrom=robert&usernameTo=johndoe"
response_status_code_should_be 2..
given_request "POST -H X-Password:j12345678 $service/friendship/request?usernameFrom=johndoe&usernameTo=robert"
response_status_code_should_be 4..

given_request "GET -H X-Password:j12345679 $service/friendship/list?username=johndoe"
response_status_code_should_be 4..
given_request "GET -H X-Password:x12345678 $service/friendship/list?username=samantha"
response_status_code_should_be 4..

given_request "GET -H X-Password:j12345678 $service/friendship/list?username=johndoe"
response_status_code_should_be 2..
response_body_should_be "[\"roseanne\",\"robert\"]"
response_content_type_should_be "application/json"

given_request "GET -H X-Password:r3456789 $service/friendship/list?username=roseanne"
response_status_code_should_be 2..
response_body_should_be "[\"johndoe\"]"
response_content_type_should_be "application/json"

given_request "GET -H X-Password:r0123456 $service/friendship/list?username=robert"
response_status_code_should_be 2..
response_body_should_be "[\"johndoe\"]"
response_content_type_should_be "application/json"

given_request "GET -H X-Password:p4567890 $service/friendship/list?username=peter"
response_status_code_should_be 2..
response_body_should_be "[]"
response_content_type_should_be "application/json"

echo -e "${color_green}All checks passed!${color_none}"
