import React from "react";
import { Link } from "react-router-dom";

const CreateProjectButton = () => {
  return (
    <React.Fragment>
      <Link to="/addProject" className="btn btn-dark btn-info">
        Novo Projeto
      </Link>
    </React.Fragment>
  );
};

export default CreateProjectButton;
