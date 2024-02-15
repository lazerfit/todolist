import axios from 'axios';

const config = {
  baseUrl: 'http://localhost:8080/api/todo/',
};

const customHeaders = {
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  },
};

async function addTodoItem(inputTodo) {
  const todo = {
    isDone: false,
    content: inputTodo,
  };

  try {
    const response = await axios.post(`${config.baseUrl}save`, JSON.stringify(todo), customHeaders);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getAllTodoItem() {
  try {
    const response = await axios.get(`${config.baseUrl}all`, customHeaders);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function deleteItem(todoId) {
  try {
    const response = await axios.post(`${config.baseUrl}delete/${todoId}`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function deleteAllItem() {
  try {
    const response = await axios.post(`${config.baseUrl}deleteAll`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function toggleChecked(id, isDone) {
  const todo = {
    id: id,
    isDone: !isDone,
  };

  try {
    const response = await axios.post(
      `${config.baseUrl}checked`,
      JSON.stringify(todo),
      customHeaders
    );
    return response;
  } catch (error) {
    console.log(error);
  }
}

export { addTodoItem, getAllTodoItem, deleteItem, deleteAllItem, toggleChecked };
