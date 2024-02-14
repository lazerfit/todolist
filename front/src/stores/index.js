import { ref } from 'vue'
import { defineStore } from 'pinia'
import { fetchTodoItem, fetchAllTodoItem } from '@/api/index'

export const useTodoStore = defineStore('todoItem', () => {
  const todoList = ref([])

  async function FETCH_TODOITEM(todoInput) {
    const response = await fetchTodoItem(todoInput)
    // console.log(response.data)
    todoList.value.push(response.data)
  }

  async function FETCH_ALLTODOITEM() {
    const response = await fetchAllTodoItem()
    todoList.value = response.data
  }

  return { todoList, FETCH_TODOITEM, FETCH_ALLTODOITEM }
})
