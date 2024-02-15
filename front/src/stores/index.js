import { ref } from 'vue';
import { defineStore } from 'pinia';
import { addTodoItem, getAllTodoItem, deleteItem, deleteAllItem, toggleChecked } from '@/api/index';

export const useTodoStore = defineStore('todoItem', () => {
  const todoList = ref([]);

  async function FETCH_TODOITEM(todoInput) {
    const response = await addTodoItem(todoInput);
    // console.log(response.data)
    todoList.value.push(response.data);
  }

  async function FETCH_ALLTODOITEM() {
    const response = await getAllTodoItem();
    todoList.value = response.data;
  }

  async function FETCH_DELETEITEM(todoId, index) {
    await deleteItem(todoId);
    todoList.value.splice(index, 1);
  }

  async function FETCH_DELETEALL() {
    await deleteAllItem();
    todoList.value.splice(0);
  }

  async function FETCH_CHECKEDTOGGLE(id, isDone, index) {
    const response = await toggleChecked(id, isDone);
    todoList.value[index].isDone = response.data.isDone;
  }

  return {
    todoList,
    FETCH_TODOITEM,
    FETCH_ALLTODOITEM,
    FETCH_DELETEITEM,
    FETCH_DELETEALL,
    FETCH_CHECKEDTOGGLE,
  };
});
