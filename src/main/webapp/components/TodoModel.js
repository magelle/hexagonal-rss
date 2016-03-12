import React from 'react';
import TodoUtils from './TodoUtils';

export default class TodoModelComponent extends React.Component {

  // Generic "model" object. You can use whatever
  // framework you want. For this application it
  // may not even be worth separating this logic
  // out, but we do this to demonstrate one way to
  // separate out parts of your application.

  constructor(key) {
    super();
    this.key = key;
    this.todos = TodoUtils.store(key);
    this.onChanges = [];

    this.subscribe = this.subscribe.bind(this);
    this.inform = this.inform.bind(this);
    this.addTodo = this.addTodo.bind(this);
    this.toggleAll = this.toggleAll.bind(this);
    this.toggle = this.toggle.bind(this);
    this.destroy = this.destroy.bind(this);
    this.save = this.save.bind(this);
    this.clearCompleted = this.clearCompleted.bind(this);

    this.onChange = this.inform;
  }

  subscribe(onChange) {
    this.onChanges.push(onChange);
  }

  inform() {
    TodoUtils.store(this.key, this.todos);
    this.onChanges.forEach(function (cb) { cb(); });
  }

  addTodo(title) {
    this.todos = this.todos.concat({
      id: TodoUtils.uuid(),
      title: title,
      completed: false
    });

    this.inform();
  }

  toggleAll(checked) {
    // Note: it's usually better to use immutable data structures since they're
    // easier to reason about and React works very well with them. That's why
    // we use map() and filter() everywhere instead of mutating the array or
    // todo items themselves.
    this.todos = this.todos.map(function (todo) {
      return TodoUtils.extend({}, todo, {completed: checked});
    });

    this.inform();
  }

  toggle(todoToToggle) {
    this.todos = this.todos.map(function (todo) {
      return todo !== todoToToggle ?
        todo :
        TodoUtils.extend({}, todo, {completed: !todo.completed});
    });

    this.inform();
  }

  destroy(todo) {
    this.todos = this.todos.filter(function (candidate) {
      return candidate !== todo;
    });

    this.inform();
  }

  save(todoToSave, text) {
    this.todos = this.todos.map(function (todo) {
      return todo !== todoToSave ? todo : TodoUtils.extend({}, todo, {title: text});
    });

    this.inform();
  }

  clearCompleted() {
    this.todos = this.todos.filter(function (todo) {
      return !todo.completed;
    });

    this.inform();
  }
}
