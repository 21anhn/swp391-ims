import React from 'react'
import { useLocation, useParams } from 'react-router-dom'

export default function Detail() {

    const {id} = useParams();
    const location = useLocation();
  return (
    <div>
        Giá trị tham số: {id}
        <br/>
        Path name hiện tại: {location.pathname}
    </div>
  )
}
