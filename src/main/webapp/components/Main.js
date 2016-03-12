require('normalize.css');
require('styles/App.css');
require('styles/Todoapp.css');

import React from 'react';
import Todoapp from './Todoapp';
import TodoModel from './TodoModel';

let yeomanImage = require('../images/yeoman.png');

export default class AppComponent extends React.Component {

  constructor() {
    super();
    this.model = new TodoModel('react-todos');
    this.render = this.render.bind(this);
    this.model.subscribe(this.render);
  }

  render() {
    return (
      <div className="index">
        <img src={yeomanImage} alt="Yeoman Generator" />
        <Todoapp model={this.model}/>
      </div>
    );
  }

}
