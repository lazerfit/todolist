<template>
  <div>
    <h2>todo it!!</h2>
  </div>
  <div>
    <input type="text" v-model="inputTodo" @keyup.enter="addTodo" />
    <span>
      <button @click="addTodo">+</button>
    </span>
  </div>
  <div>
    <ul>
      <li v-for="todo in todoList " :key="todo.id">
        {{ todo.content }}
      </li>
    </ul>
  </div>
  <div>footer</div>
</template>

<script>
import { computed, onMounted, ref } from 'vue'
import { useTodoStore } from '@/stores/index';
export default {
  setup() {
    const inputTodo = ref('')
    const store = useTodoStore()

    const todoList = computed(() => store.todoList);

    function addTodo() {
      if (inputTodo.value !== '') {
        store.FETCH_TODOITEM(inputTodo.value)
        inputTodo.value = ''
      } else {
        alert('내용을 입력해주세요.')
      }
    }

    onMounted(() => {
      store.FETCH_ALLTODOITEM()
    })

    return {
      inputTodo,
      todoList,
      addTodo
    }
  }
}
</script>
<style scoped></style>
