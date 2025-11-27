import request from "./request";

export function getStudents() {
  return request.get("/students")
}

export function addStudent(data) {
  return request.post("/students", data)
}

export function updateStudent(id, data) {
  return request.put(`/students/${id}`, data)
}

export function deleteStudent(id) {
  return request.delete(`/students/${id}`)
}
