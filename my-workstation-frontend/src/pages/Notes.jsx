import React from 'react';

const mockNotes = [
  { id: 1, title: 'First note', content: 'This is a mock note' },
  { id: 2, title: 'Second note', content: 'Another mock' },
];

export default function Notes() {
  return (
    <div>
      <h2 className="text-xl mb-3">Notes (mock)</h2>
      <ul>
        {mockNotes.map(n => (
          <li key={n.id} className="mb-2 p-3 bg-white rounded shadow">
            <strong>{n.title}</strong>
            <p>{n.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
