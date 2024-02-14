import axios from 'axios'

const config = {
  baseUrl: 'http://localhost:8080/api/todo/'
}

const customHeaders = {
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json'
  }
}

async function fetchTodoItem(inputTodo) {
  const todo = {
    isDone: false,
    content: inputTodo
  }

  try {
    const response = await axios.post(`${config.baseUrl}save`, JSON.stringify(todo), customHeaders)
    return response
  } catch (error) {
    console.log(error)
  }
}

async function fetchAllTodoItem() {
  try {
    const response = await axios.get(`${config.baseUrl}all`, customHeaders)
    return response
  } catch (error) {
    console.log(error)
  }
}

export { fetchTodoItem, fetchAllTodoItem }
