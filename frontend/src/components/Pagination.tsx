import React from "react";

interface PaginationProps {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  currentPage,
  totalPages,
  onPageChange,
}) => {
  const pages = [];

  for (let i = 1; i <= totalPages; i++) {
    pages.push(
      <li key={i} className={`page-item ${i === currentPage ? "active" : ""}`}>
        <a
          className="page-link"
          href="#"
          onClick={(e) => {
            e.preventDefault();
            onPageChange(i);
          }}
        >
          {i}
        </a>
      </li>
    );
  }

  return (
    <div>
      <nav>
        <ul className="pagination " style={{ margin: 0 }}>
          <li className={`page-item ${currentPage === 1 ? "disabled" : ""}`}>
            <a
              className="page-link"
              href="#"
              aria-label="Previous"
              onClick={(e) => {
                e.preventDefault();
                onPageChange(currentPage - 1);
              }}
            >
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          {pages}
          <li
            className={`page-item ${
              currentPage === totalPages ? "disabled" : ""
            }`}
          >
            <a
              className="page-link"
              href="#"
              onClick={(e) => {
                e.preventDefault();
                onPageChange(currentPage + 1);
              }}
            >
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Pagination;
