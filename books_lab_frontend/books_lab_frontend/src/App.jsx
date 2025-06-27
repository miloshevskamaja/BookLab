import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import React from 'react';
import './App.css'
import {BrowserRouter, Routes, Route} from "react-router";
import BooksPage from "./ui/pages/BooksPage/BooksPage.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import AuthorsPage from "./ui/pages/AuthorsPage/AuthorsPage.jsx";
import BookDetails from "./ui/components/books/BookDetails/BookDetails.jsx";
function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Layout/>}>
                  <Route index element={<HomePage/>}/>
                  <Route path="books" element={<BooksPage/>}/>
                  <Route path="authors" element={<AuthorsPage/>}/>
                  <Route path="books/:id" element={<BookDetails/>}/>
                  {/*<Route path="countries" element={<CountriesPage/>}/>*/}

              </Route>
          </Routes>
      </BrowserRouter>
  );
};

export default App
