<template>
  <div class="header">
    <h2>todo it!!</h2>
  </div>
  <div>
    <div class="input">
      <input type="text" v-model="inputTodo" @keyup.enter="addTodo" />
      <button @click="addTodo">
        <i class="fa-solid fa-plus"></i>
      </button>
    </div>
  </div>
  <div class="item-list">
    <ul>
      <li v-for="(todo, index) in  todoList  " :key="todo.id">
        <div class="item">
          <div>
            <i class="fa-solid fa-check" :class="{ checked: todo.isDone }"
              @click="toggleChecked(todo.id, todo.isDone, index)"></i>
          </div>
          <div class="item__content" :class="{ done: todo.isDone }">
            {{ todo.content }}
          </div>
          <div>
            <button @click="deleteTodo(todo.id, index)" class="del-btn">
              <i class="fa-solid fa-eraser"></i>
            </button>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="footer">
    <button @click="deleteAll">
      <i class="fa-solid fa-trash"></i>
    </button>
  </div>
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

    function deleteTodo(id, index) {
      store.FETCH_DELETEITEM(id, index)
    }

    function deleteAll() {
      store.FETCH_DELETEALL()
    }

    function toggleChecked(id, isDone, index) {
      store.FETCH_CHECKEDTOGGLE(id, isDone, index)
    }


    onMounted(() => {
      store.FETCH_ALLTODOITEM()
    })

    return {
      inputTodo,
      todoList,
      addTodo,
      deleteTodo,
      deleteAll,
      toggleChecked
    }
  }
}
</script>
<style scoped>
.fa-plus {
  font-size: 17px;
  align-content: center;
}

.fa-trash {
  font-size: 20px;
}

.fa-trash:hover,
.fa-eraser:hover {
  color: red;
}

.fa-eraser {
  font-size: 20px;
}

.item-list {
  padding-right: 40px;
}

.fa-check {
  padding-right: 7px;
  cursor: pointer;
}

.checked {
  border: 1px solid gray;
  background-color: green;
  color: white;
}

.done {
  text-decoration: line-through;
  color: gray;
}

.del-btn {
  position: fixed;
  right: 220px;
}

.item {
  width: 300px;
  justify-items: center;
  display: flex;
  flex-direction: row;
  gap: 5px;
}
</style>
