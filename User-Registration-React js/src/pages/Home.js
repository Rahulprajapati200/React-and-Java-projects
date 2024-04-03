import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';

export default function Home() {
        const [users,setUsers]= useState([]);

        const { id } =useParams()
        useEffect(()=>{
            loadUsers();
        },[])

        const loadUsers=async() =>{
            const result=await axios.get('http://localhost:8080/users');
          
             setUsers(result.data);
            console.log(result.data);
        };

        const deleteUser= async(id)=>{ 
            await axios.delete(`http://localhost:8080/deleteUser/${id}`)
            loadUsers()
        }

  return (
    <div className='container'>
        <div className='py-4'>
        <table class="table border shadow">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
                        {users.map((user, index) => (
                            <tr key={index}>
                                <th scope="row">{user.id}</th>
                                <td>{user.name}</td>
                                <td>{user.username}</td>
                                <td>{user.email}</td>
                                <td>
                                    <Link className='btn btn-primary mx-2'
                                     to={`/viewUser/${user.id}`}>View</Link>
                                    <Link className='btn btn-outline-primary mx-2'
                                    to={`/updateUser/${user.id}`}>Edit</Link>
                                    <button className='btn btn-danger mx-2'
                                    onClick={()=>deleteUser(user.id)}
                                    >Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
</table>

        </div>

    </div>
  )
}
